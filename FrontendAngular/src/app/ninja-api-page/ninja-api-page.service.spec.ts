import { TestBed } from '@angular/core/testing';

import { NinjaApiPageService } from './ninja-api-page.service';

describe('NinjaApiPageService', () => {
  let service: NinjaApiPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NinjaApiPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
