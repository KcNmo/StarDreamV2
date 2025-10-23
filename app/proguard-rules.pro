# Keep models for serialization
-keepclassmembers class ** implements java.io.Serializable { *; }
-keepattributes *Annotation*
-dontwarn kotlinx.**
-dontwarn org.intellij.lang.annotations.**
