import { TestBed } from '@angular/core/testing';

import { NewJournalEntryService } from './new-journal-entry.service';

describe('NewJournalEntryService', () => {
  let service: NewJournalEntryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NewJournalEntryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
