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


const routes: Routes = [
  {path: '', component:FitnessProgramComponent},
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
  {path: 'message-consultant', component: MessageConsultantComponent},
  { path: '**', redirectTo: '/fitness-programs', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
