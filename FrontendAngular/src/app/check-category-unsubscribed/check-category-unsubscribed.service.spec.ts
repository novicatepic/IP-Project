import { TestBed } from '@angular/core/testing';

import { CheckCategoryUnsubscribedService } from './check-category-unsubscribed.service';

describe('CheckCategoryUnsubscribedService', () => {
  let service: CheckCategoryUnsubscribedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckCategoryUnsubscribedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
