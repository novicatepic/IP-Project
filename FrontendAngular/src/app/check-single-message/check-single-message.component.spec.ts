import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckSingleMessageComponent } from './check-single-message.component';

describe('CheckSingleMessageComponent', () => {
  let component: CheckSingleMessageComponent;
  let fixture: ComponentFixture<CheckSingleMessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckSingleMessageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckSingleMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
