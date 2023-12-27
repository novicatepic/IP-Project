import { TestBed } from '@angular/core/testing';

import { SingleFitnessProgramService } from './single-fitness-program.service';

describe('SingleFitnessProgramService', () => {
  let service: SingleFitnessProgramService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SingleFitnessProgramService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
