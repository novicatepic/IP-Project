import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckCategoryUnsubscribedComponent } from './check-category-unsubscribed.component';

describe('CheckCategoryUnsubscribedComponent', () => {
  let component: CheckCategoryUnsubscribedComponent;
  let fixture: ComponentFixture<CheckCategoryUnsubscribedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckCategoryUnsubscribedComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckCategoryUnsubscribedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
