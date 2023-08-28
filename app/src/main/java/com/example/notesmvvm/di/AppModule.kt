package com.example.notesmvvm.di

import android.app.Application
import androidx.room.Room
import com.example.notesmvvm.data.repository.NoteRepositoryImpl
import com.example.notesmvvm.data.source.local.NoteDatabase
import com.example.notesmvvm.data.source.local.NoteDatabase_Impl
import com.example.notesmvvm.domain.repository.NoteRepository
import com.example.notesmvvm.domain.use_case.AddNoteUseCase
import com.example.notesmvvm.domain.use_case.DeleteNoteUseCase
import com.example.notesmvvm.domain.use_case.GetNoteByIdUseCase
import com.example.notesmvvm.domain.use_case.GetNotesUseCase
import com.example.notesmvvm.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase):NoteRepository{
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(noteRepository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            addNote = AddNoteUseCase(noteRepository),
            deleteNote = DeleteNoteUseCase(noteRepository),
            getNoteByID = GetNoteByIdUseCase(noteRepository),
            getNotes = GetNotesUseCase(noteRepository),
        )
    }
}