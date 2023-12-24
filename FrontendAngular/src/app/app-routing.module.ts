import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FitnessProgramComponent } from './fitness-program/fitness-program.component';
import { SingleFitnessProgramComponent } from './single-fitness-program/single-fitness-program.component';


const routes: Routes = [
  {path: 'fitness-programs', component:FitnessProgramComponent},
  {path: 'fitness-programs/:id', component: SingleFitnessProgramComponent},
  {path: 'third', component: FitnessProgramComponent},
  { path: '**', redirectTo: '/first', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
