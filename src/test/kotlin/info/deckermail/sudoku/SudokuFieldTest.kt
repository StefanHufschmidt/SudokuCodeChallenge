package info.deckermail.sudoku

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SudokuFieldTest {

    @Test
    @DisplayName("Check that field has list of possible values which is filled by default with 1-9")
    fun checkGetPossibleValuesOnCleanState() {
        // given: an field instance
        val sudokuField = SudokuField(1, 2)

        // and: the expected set of numbers which should be possible on clean state
        val expectedPossibleValues = HashSet<Int>()
            .apply { addAll(1..9) }

        // when: getPossibleValues
        val possibleValues = sudokuField.possibleValues

        // then: result should contain all elements of the expected set
        assertTrue(possibleValues.containsAll(expectedPossibleValues))
        assertEquals(expectedPossibleValues.size, possibleValues.size)
    }

    @Test
    @DisplayName("Check that new SudokuField does not have a value assigned")
    fun checkDefaultStateOfValue() {
        // expect: fresh instance has null as assigned value
        assertNull(SudokuField(1, 2).value)
    }

    @ParameterizedTest
    @ValueSource(ints = [1,2,3,4,5,6,7,8,9])
    @DisplayName("It should be able to set a value to a SudokuField")
    fun checkThatYouCanAssignAValueToAField(number: Int?) {
        // given: a field
        val field = SudokuField(1, 2)

        // when: assigning a value
        field.value = number

        // then: the assigned value should be set to 1
        assertEquals(number, field.value)
    }

    @Test
    @DisplayName("Check that you have to pass a x-coordinate and a y-coordinate to construct")
    fun checkConstructor() {
        // when: initialize with coordinates
        val field = SudokuField(2, 5)

        // then: x and y are set correctly
        assertEquals(2, field.x)
        assertEquals(5, field.y)
    }
}