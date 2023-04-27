package com.example.note2service.Services.Impl;

import com.example.note2service.DAO.ExamenDAO;
import com.example.note2service.DAO.NoteDAO;
import com.example.note2service.DTO.RequesteNoteDTO;
import com.example.note2service.DTO.ResponseNoteDTO;
import com.example.note2service.Entities.*;
import com.example.note2service.Exceptions.ExamenNotFoundException;
import com.example.note2service.Exceptions.NoteNotFoundException;
import com.example.note2service.Mappers.ExamenMapper;
import com.example.note2service.Mappers.NoteMapper;
import com.example.note2service.Openfeign.EtudiantRestClient;
import com.example.note2service.Services.ExamenService;
import com.example.note2service.Services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**

 This class provides the implementation of the NoteService interface, which is responsible for managing notes in the application.

 It uses NoteDAO and ExamenDAO objects for database access, NoteMapper object for mapping between DTO and entity objects,

 and EtudiantRestClient object for retrieving data from external API.
 */
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteDAO noteDAO;
    private final ExamenDAO examenDAO;
    private final NoteMapper noteMapper;
    private final EtudiantRestClient etudiantRestClient;

    /**

    * Returns a list of all notes with associated students from the database.
     * @return a list of ResponseNoteDTO objects representing the notes and their associated students
     */
    @Override
    public List<ResponseNoteDTO> getAllNotes() {
        List<Note> noteList =noteDAO.findAll();
        for ( Note n : noteList){
            n.setEtudiant(etudiantRestClient.getEtudiant(n.getId().getEtudiantId()));

        }
        return noteMapper.modelToDtos(noteList);
    }
    /**

     * Returns a note with the specified ID and associated student from the database.
     * @param id the ID of the note to retrieve
     * @return a ResponseNoteDTO object representing the note and its associated student
     * @throws NoteNotFoundException if there is no note with the specified ID
     */
    @Override
    public ResponseNoteDTO getNoteById(NoteKey id) throws NoteNotFoundException {
        Optional<Note> note =  noteDAO.findById(id);
        Etudiant etudiant = etudiantRestClient.getEtudiant(note.get().getId().getEtudiantId());
        note.get().setEtudiant(etudiant);
        if (!note.isPresent()){
            throw new NoteNotFoundException("Il n' y a aucune note avec ce ID");
        }
        return noteMapper.modelToDto(note.get());
    }
    /**

    * Adds a new note to the database.

     * @param requesteNoteDTO a RequesteNoteDTO object representing the note to add

     * @return a ResponseNoteDTO object representing the added note
     */
    @Override
    public ResponseNoteDTO addNote(RequesteNoteDTO requesteNoteDTO){

        if (requesteNoteDTO.getExamen().getType()==TypeExamen.RATTRAPAGE){
            TypeExamen typeExamen =TypeExamen.ORDINAIRE;
            String moduleName="analyse1";
            //long etudiantApogee=requesteNoteDTO.getEtudiant().getApogee();
            Etudiant etudiant=etudiantRestClient.getEtudiantByApogee(12);

           Note noteOrdinaire=noteDAO.findByEtudiantNameAndTypeExamenAndNomModule(TypeExamen.ORDINAIRE,12,"analyse1");
           System.out.println("Note Ordinaire "+noteOrdinaire.getNote());
            //System.out.println("Ordinaire "+noteOrdinaire.getNote());
            //System.out.println("Rattrapage "+requesteNoteDTO.getNote());

            /*
            if (requesteNoteDTO.getNote() > noteOrdinaire.getNote()){
                System.out.println("I'am hare");
                RequesteNoteDTO noteDTO=requesteNoteDTO;
                noteDTO.setNote(noteOrdinaire.getNote());
                noteDAO.save(noteMapper.dtoToModel(noteDTO));
            }
             */
        }
        return saveNote(requesteNoteDTO);
    }
    /**

     * Updates an existing note in the database.
     * @param requesteNoteDTO a RequesteNoteDTO object representing the note to update
     * @return a ResponseNoteDTO object representing the updated note
     */
    @Override
    public ResponseNoteDTO UpdateNote(RequesteNoteDTO requesteNoteDTO) {
        return saveNote(requesteNoteDTO);
    }
    /**
     * Saves a note to the database.
     * @param requesteNoteDTO a RequesteNoteDTO object representing the note to save
     * @return a ResponseNoteDTO object representing the saved note
     */
    private ResponseNoteDTO saveNote(RequesteNoteDTO requesteNoteDTO){
        Note note = noteMapper.dtoToModel(requesteNoteDTO);
        note.setMention(mention(note.getNote()));
        note.setExamen(examenDAO.findById(note.getId().getExamenId()).get());
        Note savedNote = noteDAO.save(note);
        savedNote.setEtudiant(etudiantRestClient.getEtudiant(savedNote.getId().getEtudiantId()));
        return noteMapper.modelToDto(savedNote);
    }
  /**
   * Returns a specific note and the corresponding student information by student ID.
   * @param etudiantId The ID of the student
 */
    @Override
    public ResponseNoteDTO NoteByEtudiant(long etudiantId) {
        return null;
    }

    @Override
    public void deleteNote(NoteKey noteId) {
                noteDAO.deleteById(noteId);
    }

    private String mention(double note){
        String mention;
        if(note >=16){
            mention="Tres Bien";
        } else if (note>=14 && note<16) {

            mention="Bien";
        } else if (note>=12 && note<14) {
            mention="Assez Bien";
        } else if (note>=10 && note<12) {
            mention="Passable";
        }else {
            mention="nv";
        }
        return mention;
    }
}
