import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckAllMessagesComponent } from './check-all-messages.component';

describe('CheckAllMessagesComponent', () => {
  let component: CheckAllMessagesComponent;
  let fixture: ComponentFixture<CheckAllMessagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckAllMessagesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckAllMessagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
