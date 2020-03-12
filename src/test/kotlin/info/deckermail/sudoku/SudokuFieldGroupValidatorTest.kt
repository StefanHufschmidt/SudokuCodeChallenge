package info.deckermail.sudoku

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SudokuFieldGroupValidatorTest {

    @Test
    @DisplayName("Check that validate will return true on correct field set")
    fun checkValidateWithCorrectData() {
        // given: correct field set
        val correctFields = listOf(
            SudokuField(0, 0, 1),
            SudokuField(0, 0, 2),
            SudokuField(0, 0, 3),
            SudokuField(0, 0, 4),
            SudokuField(0, 0, 5),
            SudokuField(0, 0, 6),
            SudokuField(0, 0, 7),
            SudokuField(0, 0, 8),
            SudokuField(0, 0, 9)
        ).shuffled().toTypedArray()

        // and: instance
        val fieldGroupValidator = SudokuFieldGroupValidator()

        // expect: true as result from validate
        assertTrue(fieldGroupValidator.validate(correctFields))
    }

    @Test
    @DisplayName("Check that validate will return true on correct field set with nulls")
    fun checkValidateWithCorrectDataWithNulls() {
        // given: correct field set
        val correctFields = listOf(
            SudokuField(0, 0, 1),
            SudokuField(0, 0, 2),
            SudokuField(0, 0),
            SudokuField(0, 0, 4),
            SudokuField(0, 0, 5),
            SudokuField(0, 0, 6),
            SudokuField(0, 0),
            SudokuField(0, 0, 8),
            SudokuField(0, 0, 9)
        ).shuffled().toTypedArray()

        // and: instance
        val fieldGroupValidator = SudokuFieldGroupValidator()

        // expect: true as result from validate
        assertTrue(fieldGroupValidator.validate(correctFields))
    }

    @ParameterizedTest
    @ValueSource(ints = [1,2,3,4,5,6,7,8,9])
    @DisplayName("Check that validate will return false on wrong field set")
    fun checkValidateWithWrongData(wrongNumber: Int) {
        // given: correct field set
        val correctFields = listOf(
            SudokuField(0, 0, wrongNumber),
            SudokuField(0, 0, wrongNumber)
        ).shuffled().toTypedArray()

        // and: instance
        val fieldGroupValidator = SudokuFieldGroupValidator()

        // expect: false as result from validate
        assertFalse(fieldGroupValidator.validate(correctFields))
    }
}