import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayRoundComponent } from './display-round.component';

describe('DisplayRoundComponent', () => {
  let component: DisplayRoundComponent;
  let fixture: ComponentFixture<DisplayRoundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DisplayRoundComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DisplayRoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
