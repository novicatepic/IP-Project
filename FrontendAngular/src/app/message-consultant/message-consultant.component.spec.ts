import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageConsultantComponent } from './message-consultant.component';

describe('MessageConsultantComponent', () => {
  let component: MessageConsultantComponent;
  let fixture: ComponentFixture<MessageConsultantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MessageConsultantComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MessageConsultantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
