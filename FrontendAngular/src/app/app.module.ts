import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { FitnessProgramComponent } from './fitness-program/fitness-program.component';
import { SingleFitnessProgramComponent } from './single-fitness-program/single-fitness-program.component';
import { NewQuestionComponent } from './new-question/new-question.component';
import { RegisterComponent } from './register/register.component';
import { CodeComponent } from './code/code.component';
import { LoginComponent } from './login/login.component';
import { ChangeProfileComponent } from './change-profile/change-profile.component';
import { PastProgramParticipationsComponent } from './past-program-participations/past-program-participations.component';
import { BuyProgramComponent } from './buy-program/buy-program.component';
import { CreateFitnessProgramComponent } from './create-fitness-program/create-fitness-program.component';
import { MyFitnessProgramsComponent } from './my-fitness-programs/my-fitness-programs.component';
import { MessageConsultantComponent } from './message-consultant/message-consultant.component';
import { CheckMessagesComponent } from './check-messages/check-messages.component';
import { CheckSingleMessageComponent } from './check-single-message/check-single-message.component';
import { CheckAllMessagesComponent } from './check-all-messages/check-all-messages.component';
import { StartPageRssComponent } from './start-page-rss/start-page-rss.component';
import { NinjaApiPageComponent } from './ninja-api-page/ninja-api-page.component';
import { JournalEntriesComponent } from './journal-entries/journal-entries.component';
import { NewJournalEntryComponent } from './new-journal-entry/new-journal-entry.component';
import { Chart } from 'chart.js';

@NgModule({
  declarations: [
    AppComponent,
    FitnessProgramComponent,
    SingleFitnessProgramComponent,
    NewQuestionComponent,
    RegisterComponent,
    CodeComponent,
    LoginComponent,
    ChangeProfileComponent,
    PastProgramParticipationsComponent,
    BuyProgramComponent,
    CreateFitnessProgramComponent,
    MyFitnessProgramsComponent,
    MessageConsultantComponent,
    CheckMessagesComponent,
    CheckSingleMessageComponent,
    CheckAllMessagesComponent,
    StartPageRssComponent,
    NinjaApiPageComponent,
    JournalEntriesComponent,
    NewJournalEntryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
