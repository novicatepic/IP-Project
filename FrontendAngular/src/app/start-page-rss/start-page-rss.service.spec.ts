import { TestBed } from '@angular/core/testing';

import { StartPageRssService } from './start-page-rss.service';

describe('StartPageRssService', () => {
  let service: StartPageRssService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StartPageRssService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
