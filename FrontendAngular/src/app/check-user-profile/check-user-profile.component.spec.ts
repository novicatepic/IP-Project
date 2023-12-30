import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckUserProfileComponent } from './check-user-profile.component';

describe('CheckUserProfileComponent', () => {
  let component: CheckUserProfileComponent;
  let fixture: ComponentFixture<CheckUserProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckUserProfileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckUserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
