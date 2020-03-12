package info.deckermail.sudoku

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SudokuInitializerTest {

    @Test
    @DisplayName("Check that initializer has set 36 values and SudokuChecker does still say it is fine")
    fun checkInitialize() {
        // given: initializer instance
        val sudokuInitializer = SudokuInitializer()

        // when: initialize a fresh board
        val board = sudokuInitializer.initializeBoard()

        // then: initialized board should be checker proven
        assertTrue(SudokuChecker().checkBoard(board))

        // and: there should be 36 values set
        assertEquals(36, board.fields.filter { it.value != null }.count())
    }
}