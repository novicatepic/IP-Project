import { TestBed } from '@angular/core/testing';

import { PastProgramParticipationsService } from './past-program-participations.service';

describe('PastProgramParticipationsService', () => {
  let service: PastProgramParticipationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PastProgramParticipationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
