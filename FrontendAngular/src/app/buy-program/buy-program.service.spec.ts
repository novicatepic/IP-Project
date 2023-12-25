import { TestBed } from '@angular/core/testing';

import { BuyProgramService } from './buy-program.service';

describe('BuyProgramService', () => {
  let service: BuyProgramService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BuyProgramService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
