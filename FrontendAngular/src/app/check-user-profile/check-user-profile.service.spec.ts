import { TestBed } from '@angular/core/testing';

import { CheckUserProfileService } from './check-user-profile.service';

describe('CheckUserProfileService', () => {
  let service: CheckUserProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckUserProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
