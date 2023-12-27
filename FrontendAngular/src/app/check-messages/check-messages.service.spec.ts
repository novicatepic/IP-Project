import { TestBed } from '@angular/core/testing';

import { CheckMessagesService } from './check-messages.service';

describe('CheckMessagesService', () => {
  let service: CheckMessagesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckMessagesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
