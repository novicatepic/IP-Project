import { TestBed } from '@angular/core/testing';

import { CheckSingleMessageService } from './check-single-message.service';

describe('CheckSingleMessageService', () => {
  let service: CheckSingleMessageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckSingleMessageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
