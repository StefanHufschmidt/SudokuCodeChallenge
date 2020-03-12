package info.deckermail.sudoku

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SudokuBoardTest {

    @Test
    @DisplayName("Check that board has 9x9 fields")
    fun checkAmountOfFieldsOnBoard() {
        assertEquals(9 * 9, SudokuBoard().fields.size)
    }

    @Test
    @DisplayName("Check that board has fields with all possible coordinates")
    fun checkBoardHasCorrectFields() {
        // given: possible coordinates
        val possibleXCoordinates = arrayOf(1,2,3,4,5,6,7,8,9)
        val possibleYCoordinates = possibleXCoordinates

        // and: a board
        val board = SudokuBoard()

        // expect: all fields should have coordinates within the possible ones
        board.fields.forEach {field ->
            assertTrue(possibleXCoordinates.contains(field.x))
            assertTrue(possibleYCoordinates.contains(field.y))
        }

        // and: there should be no duplicated coordinate combination
        assertEquals(board.fields.size, board.fields.distinctBy { "${it.x}-${it.y}" }.size)
    }

    @ParameterizedTest
    @ValueSource(ints = [1,2,3,4,5,6,7,8,9])
    @DisplayName("Check that reading a row returns only the elements of the specific row")
    fun checkReadRow(rowNum: Int) {
        // given: a board
        val board = SudokuBoard()

        // when: reading a row
        val row = board.readRow(rowNum)

        // then: a row should always contain 9 elements
        assertEquals(9, row.size)

        // and: a row should contain elements with the same y-coordinate and this should be the rowNum
        row.forEach { field ->
            assertEquals(rowNum, field.y)
        }

        // and: a row should be sorted by the x-coordinate
        for (i in 1..8) {
            assertTrue(row[i-1].x < row[i].x)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1,2,3,4,5,6,7,8,9])
    @DisplayName("Check that reading a column returns only the elements of the specific column")
    fun checkReadColumn(columnNum: Int) {
        // given: a board
        val board = SudokuBoard()

        // when: reading a column
        val column = board.readColumn(columnNum)

        // then: a row should always contain 9 elements
        assertEquals(9, column.size)

        // and: a row should contain elements with the same x-coordinate and this should be the rowNum
        column.forEach { field ->
            assertEquals(columnNum, field.x)
        }

        // and: a column should be sorted by the y-coordinate
        for (i in 1..8) {
            assertTrue(column[i-1].y < column[i].y)
        }
    }

    @Test
    @DisplayName("Check that reading blocks will return the correct field groups")
    fun checkReadBlocks() {
        // given: a board
        val board = SudokuBoard()

        // when: reading blocks
        val blocks = board.readBlocks()

        // then: 9 blocks have been returned
        assertEquals(9, blocks.size)

        // and: each of this blocks has 9 fields
        blocks.forEach { block ->
            assertEquals(9, block.size)
        }
    }

}