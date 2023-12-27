import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FitnessProgramComponent } from './fitness-program/fitness-program.component';
import { SingleFitnessProgramComponent } from './single-fitness-program/single-fitness-program.component';
import { NewQuestionComponent } from './new-question/new-question.component';
import { RegisterComponent } from './register/register.component';
import { CodeComponent } from './code/code.component';
import { LoginComponent } from './login/login.component';
import { ChangeProfileComponent } from './change-profile/change-profile.component';
import { PastProgramParticipationsComponent } from './past-program-participations/past-program-participations.component';
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
import { BuyProgramComponent } from './buy-program/buy-program.component';


const routes: Routes = [
  {path: '', component:StartPageRssComponent},
  {path: 'ninja', component:NinjaApiPageComponent},
  {path: 'journal-entries', component:JournalEntriesComponent},
  {path: 'new-journal-entry', component:NewJournalEntryComponent},
  {path: 'category-subscriptions', component:CheckCategorySubscriptionsComponent},
  {path: 'category-unsubscribed', component:CheckCategoryUnsubscribedComponent},
  {path: 'fitness-programs', component:FitnessProgramComponent},
  {path: 'login', component:LoginComponent},
  {path: 'fitness-programs/:id', component: SingleFitnessProgramComponent},
  {path: 'questions/new-question/:id', component: NewQuestionComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'code/:id', component: CodeComponent},
  {path: 'change-profile', component: ChangeProfileComponent},
  {path: 'past-program-participations', component: PastProgramParticipationsComponent},
  {path: 'unparticipated', component: PastProgramParticipationsComponent},
  {path: 'create-fitness-program', component: CreateFitnessProgramComponent},
  {path: 'my-programs', component: MyFitnessProgramsComponent},
  {path: 'buy-programs', component: BuyProgramComponent},
  {path: 'message-consultant', component: MessageConsultantComponent},
  {path: 'check-messages', component: CheckMessagesComponent},
  {path: 'all-messages', component: CheckAllMessagesComponent},
  {path: 'check-messages/:id', component: CheckSingleMessageComponent},
  { path: '**', redirectTo: '/fitness-programs', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
