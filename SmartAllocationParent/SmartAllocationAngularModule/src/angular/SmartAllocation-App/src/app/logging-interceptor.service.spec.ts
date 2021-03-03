import { TestBed, inject } from '@angular/core/testing';

import { LoggingInterceptorService } from './logging-interceptor.service';

describe('LoggingInterceptorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoggingInterceptorService]
    });
  });

  it('should be created', inject([LoggingInterceptorService], (service: LoggingInterceptorService) => {
    expect(service).toBeTruthy();
  }));
});
