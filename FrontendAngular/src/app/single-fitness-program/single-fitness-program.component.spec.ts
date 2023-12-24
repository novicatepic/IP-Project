import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleFitnessProgramComponent } from './single-fitness-program.component';

describe('SingleFitnessProgramComponent', () => {
  let component: SingleFitnessProgramComponent;
  let fixture: ComponentFixture<SingleFitnessProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SingleFitnessProgramComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SingleFitnessProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
