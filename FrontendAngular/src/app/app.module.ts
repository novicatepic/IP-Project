import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthService } from './interceptors/auth.service';

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
import { CheckCategorySubscriptionsComponent } from './check-category-subscriptions/check-category-subscriptions.component';
import { CheckCategoryUnsubscribedComponent } from './check-category-unsubscribed/check-category-unsubscribed.component';
import { ShowProfileComponent } from './show-profile/show-profile.component';
import { UploadPhotoComponent } from './upload-photo/upload-photo.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import { MainNavComponent } from './main-nav/main-nav.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';

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
    NewJournalEntryComponent,
    CheckCategorySubscriptionsComponent,
    CheckCategoryUnsubscribedComponent,
    ShowProfileComponent,
    UploadPhotoComponent,
    MainNavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
