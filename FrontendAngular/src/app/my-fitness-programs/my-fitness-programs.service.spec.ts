import { TestBed } from '@angular/core/testing';

import { MyFitnessProgramsService } from './my-fitness-programs.service';

describe('MyFitnessProgramsService', () => {
  let service: MyFitnessProgramsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyFitnessProgramsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
