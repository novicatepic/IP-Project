import { TestBed } from '@angular/core/testing';

import { AttendBoughtProgramsService } from './attend-bought-programs.service';

describe('AttendBoughtProgramsService', () => {
  let service: AttendBoughtProgramsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AttendBoughtProgramsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
