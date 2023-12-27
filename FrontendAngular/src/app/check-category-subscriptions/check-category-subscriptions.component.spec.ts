import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckCategorySubscriptionsComponent } from './check-category-subscriptions.component';

describe('CheckCategorySubscriptionsComponent', () => {
  let component: CheckCategorySubscriptionsComponent;
  let fixture: ComponentFixture<CheckCategorySubscriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckCategorySubscriptionsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckCategorySubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
