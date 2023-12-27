import { TestBed } from '@angular/core/testing';

import { CheckCategorySubscriptionsService } from './check-category-subscriptions.service';

describe('CheckCategorySubscriptionsService', () => {
  let service: CheckCategorySubscriptionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckCategorySubscriptionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
