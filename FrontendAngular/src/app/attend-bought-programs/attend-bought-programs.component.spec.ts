import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendBoughtProgramsComponent } from './attend-bought-programs.component';

describe('AttendBoughtProgramsComponent', () => {
  let component: AttendBoughtProgramsComponent;
  let fixture: ComponentFixture<AttendBoughtProgramsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AttendBoughtProgramsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AttendBoughtProgramsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
