package info.deckermail.sudoku

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SudokuCheckerTest {

    @MockK
    private lateinit var board: SudokuBoard

    @MockK
    private lateinit var fieldGroupValidator: SudokuFieldGroupValidator

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    @DisplayName("Check that checker will check rows, columns and blocks")
    fun checkCheckerChecksRowsColumnsAndBlocks() {
        // given: mocks with returning correct values
        every { fieldGroupValidator.validate(any())} returns true
        every { board.readColumn(any())} returns listOf(SudokuField(0, 0))
        every { board.readRow(any())} returns listOf(SudokuField(0, 0))
        every { board.readBlocks()} returns arrayOf(arrayOf(SudokuField(0, 0)))

        // and: instance
        val checker = SudokuChecker(fieldGroupValidator)

        // when: calling checkBoard
        val result = checker.checkBoard(board)

        // then: result should be true
        assertTrue(result)

        // and: mocked methods should have been called
        verify(exactly = 1) { board.readBlocks() }
        verify(exactly = 9) { board.readColumn(any()) }
        verify(exactly = 9) { board.readRow(any()) }
    }
}