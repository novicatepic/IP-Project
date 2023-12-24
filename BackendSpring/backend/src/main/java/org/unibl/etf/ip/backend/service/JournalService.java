package org.unibl.etf.ip.backend.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.model.DnevnikEntity;
import org.unibl.etf.ip.backend.model.DnevnikUnosEntity;
import org.unibl.etf.ip.backend.repository.JournalEntryRepository;
import org.unibl.etf.ip.backend.repository.JournalRepository;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public DnevnikEntity getJournal(Integer id) throws Exception {
        return journalRepository.findById(id).orElseThrow(() -> new RuntimeException("Exception"));
    }

    public DnevnikEntity createJournal(DnevnikEntity journal) {
       return journalRepository.save(journal);
    }

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
        return journalEntryRepository.save(journalEntry);
    }

    public void deleteJournalEntry(Integer id) {
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
            document.add(new Paragraph("Potrošeno kalorija: " + dnevnikUnos.getPotrosenoKalorija()));
            document.add(new Paragraph("-------------------------------------"));
        }

        document.close();

        return baos.toByteArray();

    }

}
