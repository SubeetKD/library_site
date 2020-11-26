from django.urls import path
from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('books/', views.BookListView.as_view(), name='books'),
    # inside the <> is captured, the first word define the type of data
    path('books/<int:pk>', views.BookDetailView.as_view(),name='book-detail'),
]
