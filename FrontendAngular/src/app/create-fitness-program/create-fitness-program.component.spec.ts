import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFitnessProgramComponent } from './create-fitness-program.component';

describe('CreateFitnessProgramComponent', () => {
  let component: CreateFitnessProgramComponent;
  let fixture: ComponentFixture<CreateFitnessProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateFitnessProgramComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateFitnessProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
