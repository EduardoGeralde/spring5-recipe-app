package com.eduardoportfolio.converters;

import com.eduardoportfolio.commands.NotesCommand;
import com.eduardoportfolio.models.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

/**
 * Created by Eduardo on 18/11/17.
 */
@Component
public class NotesCommandToNotes implements Converter<NotesCommand,Notes>{

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if(notesCommand == null){
            return null;
        }

        Notes notes = new Notes();
        notes.setId(notesCommand.getId());
        notes.setRecipeNotes(notesCommand.getRecipeNotes());
        return notes;
    }
}
