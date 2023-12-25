import { TestBed } from '@angular/core/testing';

import { MessageConsultantService } from './message-consultant.service';

describe('MessageConsultantService', () => {
  let service: MessageConsultantService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MessageConsultantService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
