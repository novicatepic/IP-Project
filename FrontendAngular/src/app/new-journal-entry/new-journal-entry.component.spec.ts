import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewJournalEntryComponent } from './new-journal-entry.component';

describe('NewJournalEntryComponent', () => {
  let component: NewJournalEntryComponent;
  let fixture: ComponentFixture<NewJournalEntryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NewJournalEntryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NewJournalEntryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
