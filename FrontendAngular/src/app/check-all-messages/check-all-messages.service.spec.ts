import { TestBed } from '@angular/core/testing';

import { CheckAllMessagesService } from './check-all-messages.service';

describe('CheckAllMessagesService', () => {
  let service: CheckAllMessagesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckAllMessagesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
