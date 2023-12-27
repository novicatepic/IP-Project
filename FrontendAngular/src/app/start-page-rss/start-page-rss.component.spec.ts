import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StartPageRssComponent } from './start-page-rss.component';

describe('StartPageRssComponent', () => {
  let component: StartPageRssComponent;
  let fixture: ComponentFixture<StartPageRssComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StartPageRssComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StartPageRssComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
