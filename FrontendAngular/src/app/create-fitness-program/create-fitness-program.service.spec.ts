import { TestBed } from '@angular/core/testing';

import { CreateFitnessProgramService } from './create-fitness-program.service';

describe('CreateFitnessProgramService', () => {
  let service: CreateFitnessProgramService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateFitnessProgramService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
