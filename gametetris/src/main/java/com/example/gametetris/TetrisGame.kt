package com.example.gametetris

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType.Companion.KeyDown
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


enum class Tetromino {
    I, O, T, S, Z, J, L
}

data class Block(val x: Int, val y: Int)

data class TetrisPiece(val tetromino: Tetromino, val blocks: List<Block>)


data class TetrisState(
    val piece: TetrisPiece,
    val grid: List<List<Boolean>>,
    val score: Int
)

fun newPiece(): TetrisPiece {
    return TetrisPiece(
        tetromino = Tetromino.values().random(),
        blocks = listOf(Block(4, 0), Block(5, 0), Block(6, 0), Block(7, 0))
    )
}

fun movePiece(piece: TetrisPiece, dx: Int, dy: Int): TetrisPiece {
    return piece.copy(blocks = piece.blocks.map { Block(it.x + dx, it.y + dy) })
}

fun rotatePiece(piece: TetrisPiece): TetrisPiece {
    val center = piece.blocks[1]
    val rotated = piece.blocks.map {
        Block(center.x - (it.y - center.y), center.y + (it.x - center.x))
    }
    return piece.copy(blocks = rotated)
}

fun canMove(state: TetrisState, dx: Int, dy: Int, piece: TetrisPiece? = null): Boolean {
    val movingPiece = piece ?: state.piece
    val newPiece = movePiece(movingPiece, dx, dy)
    return newPiece.blocks.all { block ->
        block.x in 0..9 && block.y in 0..19 && !state.grid[block.y][block.x]
    }
}

fun canRotate(state: TetrisState, piece: TetrisPiece? = null): Boolean {
    val movingPiece = piece ?: state.piece
    val rotated = rotatePiece(movingPiece)
    return rotated.blocks.all { block ->
        block.x in 0..9 && block.y in 0..19 && !state.grid[block.y][block.x]
    }
}

fun mergePieceWithGrid(grid: List<List<Boolean>>, piece: TetrisPiece): List<List<Boolean>> {
    val newGrid = grid.map { it.toMutableList() }
    piece.blocks.forEach { newGrid[it.y][it.x] = true }
    return newGrid
}

fun placePiece(state: TetrisState): TetrisState {
    val newGrid = mergePieceWithGrid(state.grid, state.piece)
    return state.copy(piece = newPiece(), grid = newGrid)
}

fun clearLines(state: TetrisState): TetrisState {
    val fullLines = state.grid.filter { it.all { cell -> cell } }
    val grid = state.grid.filter { !it.all { cell -> cell } }
    val filled = List(state.grid.size - grid.size) { List(state.grid[0].size) { true } }
    val score = state.score + fullLines.size * 100
    return state.copy(grid = filled + grid, score = score)
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TetrisGame() {
    val cellSize = 20.dp
    var state by remember {
        mutableStateOf(
            TetrisState(
                newPiece(),
                List(20) { List(10) { false } },
                0
            )
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(500L)
            if (canMove(state, 0, 1)) {
                state = state.copy(piece = movePiece(state.piece, 0, 1))
            } else {
                val newState = placePiece(state)
                state = clearLines(newState)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .onKeyEvent { event: KeyEvent ->
                if (event.type == KeyDown) {
                    when (event.key) {
                        Key.DirectionLeft -> if (canMove(state, -1, 0)) state =
                            state.copy(piece = movePiece(state.piece, -1, 0))

                        Key.DirectionRight -> if (canMove(state, 1, 0)) state =
                            state.copy(piece = movePiece(state.piece, 1, 0))

                        Key.DirectionDown -> if (canMove(state, 0, 1)) state =
                            state.copy(piece = movePiece(state.piece, 0, 1))

                        Key.Spacebar -> if (canRotate(state)) state =
                            state.copy(piece = rotatePiece(state.piece))
                    }
                }
                true
            },
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.size(cellSize.value.dp.times(10), cellSize.value.dp.times(20))) {
            Text(text = "Score: ${state.score}", fontSize = 24.sp)
        }
        Canvas(
            modifier = Modifier
                .size(cellSize.value.dp.times(10), cellSize.value.dp.times(20))
                .background(Color.White)
                .focusable(true)
        ) {
            val mergedGrid = mergePieceWithGrid(state.grid, state.piece)
            mergedGrid.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    if (cell) {
                        drawRect(
                            color = Color.Black,
                            topLeft = Offset(
                                x * cellSize.toPx(),
                                y * cellSize.toPx()
                            ),
                            size = Size(cellSize.toPx(), cellSize.toPx())
                        )
                    }
                }
            }
        }
    }
}



