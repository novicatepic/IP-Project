import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PastProgramParticipationsComponent } from './past-program-participations.component';

describe('PastProgramParticipationsComponent', () => {
  let component: PastProgramParticipationsComponent;
  let fixture: ComponentFixture<PastProgramParticipationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PastProgramParticipationsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PastProgramParticipationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
