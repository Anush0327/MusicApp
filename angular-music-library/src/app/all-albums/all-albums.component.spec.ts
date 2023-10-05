import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAlbumsComponent } from './all-albums.component';

describe('AllAlbumsComponent', () => {
  let component: AllAlbumsComponent;
  let fixture: ComponentFixture<AllAlbumsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllAlbumsComponent]
    });
    fixture = TestBed.createComponent(AllAlbumsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
