import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NinjaApiPageComponent } from './ninja-api-page.component';

describe('NinjaApiPageComponent', () => {
  let component: NinjaApiPageComponent;
  let fixture: ComponentFixture<NinjaApiPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NinjaApiPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NinjaApiPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
