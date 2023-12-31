package org.unibl.etf.ip.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.unibl.etf.ip.backend.model.KategorijaEntity;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.model.ProgramEntity;
import org.unibl.etf.ip.backend.repository.FitnessUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledMailService {

    @Autowired
    private FitnessUserRepository userRepository;

    @Autowired
    private FitnessProgramService programService;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "5 20 16 * * ?")
    public void sendMailToUsers() {
        List<KorisnikEntity> fitnessUsers = userRepository.findAll();


        for(KorisnikEntity fitnessUser : fitnessUsers) {
            List<ProgramEntity> toSend = new ArrayList<>();
            for(KategorijaEntity category : fitnessUser.getSubscribedCategories()) {
                List<ProgramEntity> programs = programService.getProgramsFromLastDay(category.getId());
                List<ProgramEntity> filteredPrograms = programs.stream()
                        .filter(program -> !program.getKreatorId().equals(fitnessUser.getId()))
                        .collect(Collectors.toList());
                toSend.addAll(filteredPrograms);
            }
            StringBuilder sb = new StringBuilder();
            for(ProgramEntity programToSend : toSend) {
                String tempWeight;

                if(programToSend.getTezina() == 0) {
                    tempWeight = "Easy";
                } else if (programToSend.getTezina() == 1) {
                    tempWeight = "Medium";
                } else {
                    tempWeight = "Hard";
                }
                String formattedMessage = String.format(
                        "-------------------------------------------\n" +
                                "New Program Details:\n" +
                                "Name: %s\n" +
                                "Description: %s\n" +
                                "Price: %s\n" +
                                "Difficulty: %s\n" +
                                "Duration: %s minutes\n" +
                                "Contact: %s\n" +
                                "Available until: %s\n" +
                                "Created by: %s\n" +
                                "Category: %s\n" +
                                "Instructor: %s %s\n" +
                                "-------------------------------------------",
                        programToSend.getNaziv(),
                        programToSend.getOpis(),
                        programToSend.getCijena(),
                        tempWeight,
                        programToSend.getTrajanje(),
                        programToSend.getKontakt(),
                        programToSend.getDatum(),
                        programToSend.getKreator().getKorisnickoIme(),
                        programToSend.getKategorija().getNaziv(),
                        programToSend.getInstruktorIme(),
                        programToSend.getInstruktorPrezime()
                );
                sb.append(formattedMessage);
            }
            mailService.sendEmail(fitnessUser.getMail(), "New programs list", sb.toString());
        }

    }

}
