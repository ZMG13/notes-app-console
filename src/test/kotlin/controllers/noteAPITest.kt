package controllers

import junit.framework.Assert.assertEquals
import models.Note
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

//import kotlin.test.assertEquals

class NoteAPITest {

    private var learnKotlin: Note? = null
    private var summerHoliday: Note? = null
    private var codeApp: Note? = null
    private var testApp: Note? = null
    private var swim: Note? = null
    private var populatedNotes: NoteAPI? = NoteAPI()
    private var emptyNotes: NoteAPI? = NoteAPI()

    @BeforeEach
    fun setup(){
        learnKotlin = Note("Learning Kotlin", 5, "College", false)
        summerHoliday = Note("Summer Holiday to France", 1, "Holiday", false)
        codeApp = Note("Code App", 4, "Work", false)
        testApp = Note("Test App", 4, "Work", false)
        swim = Note("Swim - Pool", 3, "Hobby", false)

        //adding 5 Note to the notes api
        populatedNotes!!.add(learnKotlin!!)
        populatedNotes!!.add(summerHoliday!!)
        populatedNotes!!.add(codeApp!!)
        populatedNotes!!.add(testApp!!)
        populatedNotes!!.add(swim!!)
    }

    @AfterEach
    fun tearDown(){
        learnKotlin = null
        summerHoliday = null
        codeApp = null
        testApp = null
        swim = null
        populatedNotes = null
        emptyNotes = null
    }

    @Nested
    inner class AddNotes {
        @Test
        fun `adding a Note to a populated list adds to ArrayList`() {
            val newNote = Note("Study Lambdas", 1, "College", false)
            assertEquals(5, populatedNotes!!.numberOfNotes())
            assertTrue(populatedNotes!!.add(newNote))
            assertEquals(6, populatedNotes!!.numberOfNotes())
            assertEquals(newNote, populatedNotes!!.findNote(populatedNotes!!.numberOfNotes() - 1))
        }

        @Test
        fun `adding a Note to an empty list adds to ArrayList`() {
            val newNote = Note("Study Lambdas", 1, "College", false)
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(emptyNotes!!.add(newNote))
            assertEquals(1, emptyNotes!!.numberOfNotes())
            assertEquals(newNote, emptyNotes!!.findNote(emptyNotes!!.numberOfNotes() - 1))
        }
    }

    @Nested
    inner class ListNotes {

        @Test
        fun `listAllNotes returns No Notes Stored message when ArrayList is empty`() {
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(emptyNotes!!.listAllNotes().lowercase().contains("no notes"))
        }

        @Test
        fun `listAllNotes returns Notes when ArrayList has notes stored`() {
            assertEquals(5, populatedNotes!!.numberOfNotes())
            val notesString = populatedNotes!!.listAllNotes().lowercase()
            assertTrue(notesString.contains("learning kotlin"))
            assertTrue(notesString.contains("code app"))
            assertTrue(notesString.contains("test app"))
            assertTrue(notesString.contains("swim"))
            assertTrue(notesString.contains("summer holiday"))
        }
    }
    @Test
    fun `listActiveNotes returns no active notes stored when ArrayList is empty`() {
        assertEquals(0, emptyNotes!!.numberOfActiveNotes())
        assertTrue(
            emptyNotes!!.listActiveNotes().lowercase().contains("no active notes")
        )
    }

    @Test
    fun `listActiveNotes returns active notes when ArrayList has active notes stored`() {
        assertEquals(3, populatedNotes!!.numberOfActiveNotes())
        val activeNotesString = populatedNotes!!.listActiveNotes().lowercase()
        assertTrue(activeNotesString.contains("learning kotlin"))
        assertFalse(activeNotesString.contains("code app"))
        assertTrue(activeNotesString.contains("summer holiday"))
        assertTrue(activeNotesString.contains("test app"))
        assertFalse(activeNotesString.contains("swim"))
    }

    @Test
    fun `listArchivedNotes returns no archived notes when ArrayList is empty`() {
        assertEquals(0, emptyNotes!!.numberOfArchivedNotes())
        assertTrue(
            emptyNotes!!.listArchivedNotes().lowercase().contains("no archived notes")
        )
    }

    @Test
    fun `listArchivedNotes returns archived notes when ArrayList has archived notes stored`() {
        assertEquals(2, populatedNotes!!.numberOfArchivedNotes())
        val archivedNotesString = populatedNotes!!.listArchivedNotes().lowercase(Locale.getDefault())
        assertFalse(archivedNotesString.contains("learning kotlin"))
        assertTrue(archivedNotesString.contains("code app"))
        assertFalse(archivedNotesString.contains("summer holiday"))
        assertFalse(archivedNotesString.contains("test app"))
        assertTrue(archivedNotesString.contains("swim"))
    }

}