import { TestBed } from '@angular/core/testing';

import { GetEmployeeDetailsService } from './get-employee-details.service';

describe('GetEmployeeDetailsService', () => {
  let service: GetEmployeeDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetEmployeeDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
