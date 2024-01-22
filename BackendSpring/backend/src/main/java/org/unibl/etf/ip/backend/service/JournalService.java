package org.unibl.etf.ip.backend.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.model.DnevnikEntity;
import org.unibl.etf.ip.backend.model.DnevnikUnosEntity;
import org.unibl.etf.ip.backend.repository.DnevnikRepository;
import org.unibl.etf.ip.backend.repository.JournalEntryRepository;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    private Logger logger = LoggerFactory.getLogger(JournalService.class);

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private DnevnikRepository journalRepository;

    public List<DnevnikUnosEntity> getJournalEntries(Integer id) {
        List<DnevnikUnosEntity> entries = journalEntryRepository.findAll();
        ArrayList<DnevnikUnosEntity> result = new ArrayList<>();
        for(DnevnikUnosEntity entry :entries) {
            if(entry.getDnevnikKorisnikId() == id) {
                result.add(entry);
            }
        }
        return result;
    }

    public DnevnikUnosEntity createJournalEntry(DnevnikUnosEntity journalEntry) {
        logger.info("User with id " + journalEntry.getDnevnikKorisnikId() + " created new journal entry");
        Optional<DnevnikEntity> find = journalRepository.findById(journalEntry.getDnevnikKorisnikId());
        if(!find.isPresent()) {
            DnevnikEntity entity = new DnevnikEntity();
            entity.setId(journalEntry.getDnevnikKorisnikId());
            journalRepository.save(entity);
        }
        return journalEntryRepository.save(journalEntry);
    }

    public void deleteJournalEntry(Integer id, Integer userId) throws NotFoundException, MethodNotAllowedException {
        Optional<DnevnikUnosEntity> journalEntryOptional = journalEntryRepository.findById(id);

        if(!journalEntryOptional.isPresent()) {
            throw new NotFoundException();
        }
        DnevnikUnosEntity journalEntry = journalEntryOptional.get();
        if(journalEntry.getDnevnikKorisnikId() != userId) {
            throw new MethodNotAllowedException();
        }

        journalEntryRepository.deleteById(id);
    }

    public byte[] makePDF(Integer id) throws Exception {
        List<DnevnikUnosEntity> journalEntries = getJournalEntries(id);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        for (DnevnikUnosEntity dnevnikUnos : journalEntries) {
            document.add(new Paragraph("Vježba: " + dnevnikUnos.getVjezba()));
            document.add(new Paragraph("Trajanje: " + dnevnikUnos.getTrajanje()));
            document.add(new Paragraph("Intenzitet: " + dnevnikUnos.getIntenzitet()));
            document.add(new Paragraph("Kilaža: " + dnevnikUnos.getKilaza()));
            document.add(new Paragraph("-------------------------------------"));
        }

        document.close();

        return baos.toByteArray();

    }

}
