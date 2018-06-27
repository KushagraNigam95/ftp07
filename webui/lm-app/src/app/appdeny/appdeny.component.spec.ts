import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppdenyComponent } from './appdeny.component';

describe('AppdenyComponent', () => {
  let component: AppdenyComponent;
  let fixture: ComponentFixture<AppdenyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppdenyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppdenyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
